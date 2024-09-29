package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.pet.Pet;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PetFinder {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Pet findPetById(List<Pet> pets, long id) {
        Optional<Pet> pet = pets.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
        return pet.orElse(null); // Return the pet if found, otherwise return null
    }

    public static Pet convertMapToPet(Map<String, Object> map) {
        return objectMapper.convertValue(map, Pet.class);
    }

    public static List<Pet> convertMapListToPetList(List<Map<String, Object>> mapList) {
        return mapList.stream()
                .map(PetFinder::convertMapToPet)
                .collect(Collectors.toList());
    }
}
