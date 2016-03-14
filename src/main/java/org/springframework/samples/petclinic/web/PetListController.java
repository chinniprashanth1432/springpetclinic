package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PetListController {
	
	private final ClinicService clinicService;
	
	@Autowired
    public PetListController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @RequestMapping(value = {"/pets.xml", "/pets.html"})
    public String showPetList(Map<String, Object> model) {
        // Here we are returning an object of type 'Pets' rather than a collection of Pet objects
        // so it is simpler for Object-Xml mapping
        Pets pets = new Pets();
        pets.getPetList().addAll(this.clinicService.findPets());
        model.put("pets", pets);
        return "pets/petList";
    }

    @RequestMapping("/pets.json")
    public
    @ResponseBody
    Pets showResourcesPetList() {
        // Here we are returning an object of type 'Pets' rather than a collection of Pet objects
        // so it is simpler for JSon/Object mapping
        Pets pets = new Pets();
        pets.getPetList().addAll(this.clinicService.findPets());
        return pets;
    }    
}
