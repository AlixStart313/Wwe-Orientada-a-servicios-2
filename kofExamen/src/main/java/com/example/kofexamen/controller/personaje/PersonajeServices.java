package com.example.kofexamen.controller.personaje;

import com.example.kofexamen.model.personaje.BeanPersonaje;
import com.example.kofexamen.model.personaje.DaoPersonajes;
import com.example.kofexamen.utils.Response;
import com.example.kofexamen.model.personaje.BeanPersonaje;
import com.example.kofexamen.model.personaje.DaoPersonajes;
import com.example.kofexamen.utils.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/personaje")
public class PersonajeServices {
    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanPersonaje> getAll(){
        return new DaoPersonajes().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response<BeanPersonaje> find(@PathParam("id")Long id ){

        return new DaoPersonajes().findById(id);
    }

    @POST//Insercion
    @Path("/save") //"api/personal"
    @Consumes(MediaType.APPLICATION_JSON)//Consume Json
    @Produces(MediaType.APPLICATION_JSON)// Retorna Json
    public Response<BeanPersonaje> save(BeanPersonaje personaje){
        return  new DaoPersonajes().save(personaje);
    }

    @POST//Insercion
    @Path("/update") //"api/personal"
    @Consumes(MediaType.APPLICATION_JSON)//Consume Json
    @Produces(MediaType.APPLICATION_JSON)// Retorna Json
    public Response<BeanPersonaje> update(BeanPersonaje personje){
        return  new DaoPersonajes().update(personje);
    }


    @POST
    @Path("/delete/{id}") //"api/personal"
    @Produces(value = {"application/json"})
    public Response<BeanPersonaje> delete(@PathParam("id")Long id ){
        return new DaoPersonajes().delete(id);
    }

}
