package com.example.kofexamen.controller.lucha;



import com.example.kofexamen.model.lucha.BeanLucha;
import com.example.kofexamen.model.lucha.DaoLucha;
import com.example.kofexamen.utils.Response;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/api/lucha")
public class LuchaServices {
    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanLucha> getAll(){
        return new DaoLucha().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response<BeanLucha> findgetById(@PathParam("id")Long id ){
        return new DaoLucha().findById(id);
    }
}
