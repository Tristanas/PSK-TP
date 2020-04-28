package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Pokemon;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.PokemonDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Named
@ViewScoped
public class MyPokemon implements Serializable {
    @Inject
    private PokemonDAO pokemonDAO;

    @Getter
    @Setter
    private Pokemon viewedPokemon;

    // For troubleshooting
    @ManagedProperty("#{request.requestURL}")
    private String url;

    @PostConstruct
    private void init() {
        System.out.println("MyPokemon is being constructed. URL:" + url);
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer pokemonId;

        try {
            pokemonId  = Integer.parseInt(requestParameters.get("pokemonId"));
            viewedPokemon = pokemonDAO.findOne(pokemonId);
        } catch (NumberFormatException e) {
            // A redirect is likely occurring.
            viewedPokemon = new Pokemon();
            System.out.print("Redirect occurred.");
        }

    }

    private String getRefreshUrl() {
        return "pokemon?faces-redirect=true&pokemonId=" + viewedPokemon.getId();
    }

    @Transactional
    public String powerUp() {
        viewedPokemon.powerUp();
        pokemonDAO.update(viewedPokemon);
        return getRefreshUrl();
    }

    @Transactional
    public String rename() {
        pokemonDAO.update(viewedPokemon);
        return getRefreshUrl();
    }

    // Not sure why deleting doesnt work. Also, redirect to trainer page does not work. It seems like first the page is refreshed, then parsing occurs and fails.
    @Transactional
    @LoggedInvocation
    public String transfer() {
        System.out.println("Deleting: " + viewedPokemon.getName() + " named level " + viewedPokemon.getLevel() + " pokemon");
        pokemonDAO.remove(viewedPokemon);
        return "trainer?faces-redirect=true";
    }
}
