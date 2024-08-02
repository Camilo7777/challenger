package com.mercadolibre.challenger.services;

import com.mercadolibre.challenger.models.BlockedIp;
import com.mercadolibre.challenger.models.Ip;
import com.mercadolibre.challenger.repositories.BlockedIPRepository;
import com.mercadolibre.challenger.repositories.IpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class IPService {

    @Autowired
    private IpRepository ipRepository;
    @Autowired
    private BlockedIPRepository blockedIPRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final String TOR_NODES_URL = "https://www.dan.me.uk/torlist/?exit";

   // private static final String TOR_NODES_URL = "https://www.dan.me.uk/torlist/?full";


    public void getAllIpsForTor() {
        WebClient webClient = webClientBuilder.build();

        String response = webClient.get()
                .uri(TOR_NODES_URL)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (response != null) {
            String[] ips = response.split("\\s+");
            List<Ip> ipList = new ArrayList<>();

            for (String ip : ips) {
                Ip torIP = new Ip();
                torIP.setIp(ip);
                ipList.add(torIP);
            }

            ipRepository.saveAll(ipList);

            /*
             for (String ip : ips) {
                Ip torIP = new Ip();
                torIP.setIp(ip);
                ipRepository.save(torIP);

            }

             */
        }
    }


    public List<Ip> getAll() {
        return ipRepository.findAll();
    }


    public List<Ip> getAllExcludes() {
         List<BlockedIp> blockedIPS = blockedIPRepository.findAll();
         List<Ip> ipList = ipRepository.findAll();

        // Extraer las direcciones IP de ipMalaList
        Set<String> blockedIPSet = blockedIPS.stream()
                .map(BlockedIp::getIp)
                .collect(Collectors.toSet());

        // Filtrar ipList para excluir los elementos que están en ipMalaSet
        return ipList.stream()
                .filter(ip -> !blockedIPSet.contains(ip.getIp()))
                .toList();

    }

    public void blockIP(String ip) {
        if (!blockedIPRepository.existsByIp(ip)) {
            BlockedIp ipMala = new BlockedIp();
            ipMala.setIp(ip);
            blockedIPRepository.save(ipMala);
        }
    }
}