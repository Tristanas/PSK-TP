package lt.vu.usecases;

import lt.vu.entities.GymBadge;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.GymBadgeStatistics;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class ViewGymBadgeStatistics implements Serializable {
    @Inject
    GymBadgeStatistics statistics;

    private CompletableFuture<Integer> statisticsRetrievalTask = null;

    @LoggedInvocation
    public String getGymBadgeStatistics(Integer id) {
        System.out.println("Parameter passed from UI: " + id);
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String gymId = requestParameters.get("gymId");
        statisticsRetrievalTask = CompletableFuture.supplyAsync(() -> statistics.getBadgeEarnedCount(id));

        return  "/gym.xhtml?faces-redirect=true&gymId=" + gymId;
    }

    public boolean isStatisticsRetrievalRunning() {
        return statisticsRetrievalTask != null && !statisticsRetrievalTask.isDone();
    }

    public String getStatisticsRetrievalStatus() throws ExecutionException, InterruptedException {
        if (statisticsRetrievalTask == null) {
            return null;
        } else if(isStatisticsRetrievalRunning()) {
            return "Statistics retrieval in progress";
        }
        return "Gym badge is earned: " + statisticsRetrievalTask.get();
    }
}
