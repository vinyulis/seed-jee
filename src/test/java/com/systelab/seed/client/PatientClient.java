package com.systelab.seed.client;

import com.systelab.seed.model.patient.Patient;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PatientClient extends BaseClient {
    public Patient create(Patient patient) throws RequestException {
        WebTarget target = this.getWebTarget().path("patients/patient");

        Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, getAuthenticationToken()).post(Entity.entity(patient, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            throw new RequestException(response.getStatus());
        }

        return response.readEntity(Patient.class);
    }

    public Patient get(Long id) throws RequestException {
        WebTarget target = this.getWebTarget().path("patients/" + id);

        Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, getAuthenticationToken()).get(Response.class);

        if (response.getStatus() != 200) {
            throw new RequestException(response.getStatus());
        }
        return response.readEntity(Patient.class);
    }

    public List<Patient> get() throws RequestException {
        WebTarget target = this.getWebTarget().path("patients");

        Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, getAuthenticationToken()).get(Response.class);

        if (response.getStatus() != 200) {
            throw new RequestException(response.getStatus());
        }

        return response.readEntity(new GenericType<List<Patient>>() {
        });
    }

    public boolean delete(Long id) throws RequestException {
        WebTarget target = this.getWebTarget().path("patients/" + id);

        Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, getAuthenticationToken()).delete(Response.class);

        if (response.getStatus() != 200) {
            throw new RequestException(response.getStatus());
        }
        return true;
    }
}
