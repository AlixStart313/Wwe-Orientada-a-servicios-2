package com.example.kofexamen.controller.magia;


import com.example.kofexamen.model.magia.BeanMagia;
import com.example.kofexamen.model.magia.DaoMagia;
import com.example.kofexamen.utils.Response;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/magia")
public class MagicaServices {
    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanMagia> getAll(){
        return new DaoMagia().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response<BeanMagia> findgetById(@PathParam("id")Long id ){

        return new DaoMagia().findById(id);
    }
}
