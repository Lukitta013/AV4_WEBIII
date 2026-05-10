package com.autobots.automanager.dtos.response;

import com.autobots.automanager.enums.TipoDocumento;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class DocumentoResponseDTO extends RepresentationModel<DocumentoResponseDTO> {
    private Long id;
    private TipoDocumento tipo;
    private String numero;
}