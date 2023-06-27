package com.microservice.academia.domain.model.ports.api;

import com.microservice.academia.domain.model.model.pensun.Pensum;

public interface CreatePensumServicePort {
    Pensum createPensum(Pensum pensum);
}
