package com.systelab.seed.client;

import com.systelab.seed.model.patient.Patient;
import com.systelab.seed.util.pagination.Page;
import io.qameta.allure.Step;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

public class PatientClient extends BaseClient {

    @Step("Create the patient {0}")
    public Patient create(Patient patient) throws RequestException {
        WebTarget target = this.getWebTarget().path("patients/patient");

        Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, getAuthenticationToken()).post(Entity.entity(patient, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            throw new RequestException(response.getStatus());
        }
        return response.readEntity(Patient.class);
    }

    @Step("Get the patient with id {0}")
    public Patient get(UUID id) throws RequestException {
        WebTarget target = this.getWebTarget().path("patients/" + id.toString());

        Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, getAuthenticationToken()).get(Response.class);

        if (response.getStatus() != 200) {
            throw new RequestException(response.getStatus());
        }
        return response.readEntity(Patient.class);
    }

    @Step("Get the patient list")
    public Page<Patient> get() throws RequestException {
        WebTarget target = this.getWebTarget().path("patients");

        Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, getAuthenticationToken()).get(Response.class);

        if (response.getStatus() != 200) {
            throw new RequestException(response.getStatus());
        }
        return response.readEntity(new GenericType<Page<Patient>>() {
        });
    }

    @Step("Delete the patient {0}")
    public boolean delete(UUID id) throws RequestException {
        WebTarget target = this.getWebTarget().path("patients/" + id.toString());

        Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, getAuthenticationToken()).delete(Response.class);

        if (response.getStatus() != 200) {
            throw new RequestException(response.getStatus());
        }
        return true;
    }
}
