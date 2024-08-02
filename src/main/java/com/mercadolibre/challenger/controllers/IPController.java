package com.mercadolibre.challenger.controllers;

import com.mercadolibre.challenger.models.Ip;
import com.mercadolibre.challenger.services.IPService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ips")
@Tag(name = "Administrador de IPS", description = "Endpoints para el manejo de las IP")
public class IPController {
    @Autowired
    private IPService ipService;

    @GetMapping("/getAllIpsForTor")
    @Operation(
            summary = "Obtener IPS de TOR y almacenarlas",
            description = "Obtiene las IP de TOR y las almacena en la base de datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "IPs retrieved successfully")
            }
    )
    public String getAllIps() {
        ipService.getAllIpsForTor();
        return "Se han almacenado las Ips de forma correcta";
    }


    @GetMapping("/")
    @Operation(
            summary = "Obtener todas las IP",
            description = "Obtiene todas las IP almacenadas en la base datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of IPs retrieved successfully")
            }
    )
    public List<Ip> getAll() {
        return ipService.getAll();
    }

    @PostMapping("/block")
    @Operation(
            summary = "Ingresar IP a bloquear",
            description = "Se agrega la ip a la lista de bloqueadas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "IP blocked successfully")
            }
    )
    public void blockIP(
            @Parameter(description = "Direccion Ip a bloquear", required = true) @RequestBody String ip
    ) {
        ipService.blockIP(ip);
    }


    @GetMapping("/excludes")
    @Operation(
            summary = "Obtener IPS excluidas",
            description = "Trae las IP excluidas solamente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of excluded IPs retrieved successfully"),
            }
    )
    public List<Ip> getAllExcludes() {
        return ipService.getAllExcludes();
    }

}
