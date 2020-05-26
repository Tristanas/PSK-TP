package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.cdi.interceptors.LoggedInvocation;
import lt.vu.entities.Trainer;
import lt.vu.persistence.TrainersDAO;
import lt.vu.rest.contracts.TrainerDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/trainers")
public class TrainersController {
    @Inject
    @Getter @Setter
    private TrainersDAO trainersDAO;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Trainer> trainers = trainersDAO.findAll();
        List<TrainerDTO> shownTrainers = new ArrayList<>();
        for (Trainer tr: trainers
             ) {
            shownTrainers.add(trainerToDto(tr));
        }
        return Response.ok(shownTrainers).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @LoggedInvocation
    public Response getById(@PathParam("id") final Integer id) {
        System.out.println(id);
        Trainer trainer = trainersDAO.findOne(id);
        if (trainer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        TrainerDTO trainerDto = trainerToDto(trainer);

        return Response.ok(trainer).build();
    }

    private TrainerDTO trainerToDto(Trainer trainer) {
        TrainerDTO trainerDto = new TrainerDTO();
        trainerDto.setUsername(trainer.getUsername());
        trainerDto.setLevel(trainer.getLevel());
        trainerDto.setXp(trainer.getXp());
        trainerDto.setId(trainer.getId());
        return trainerDto;
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Trainer trainer) {
        Trainer existingTrainer = trainersDAO.findOne(trainer.getId());
        if (existingTrainer != null) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        trainersDAO.persist(trainer);
        return Response.created(URI.create("/api/trainers/"+trainer.getId())).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer trainerId,
            TrainerDTO trainerData) {
        try {
            Trainer existingTrainer = trainersDAO.findOne(trainerId);
            if (existingTrainer == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingTrainer.setUsername(trainerData.getUsername());
            existingTrainer.setLevel(trainerData.getLevel());
            existingTrainer.setXp(trainerData.getXp());
            trainersDAO.update(existingTrainer);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response delete(
            @PathParam("id") final Integer trainerId) {
        try {
            Trainer existingTrainer = trainersDAO.findOne(trainerId);
            if (existingTrainer == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            trainersDAO.delete(existingTrainer);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
