package it.univr.WeatherStation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.univr.WeatherStation.Station;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    private Sensor windSensor = new Sensor(0);
    private Sensor temperatureSensor = new Sensor(19);
    private Battery batteryLevel = new Battery(30);
    private Station weatherStation = new Station(windSensor, temperatureSensor, new Sensor(50), new Sensor(50000), batteryLevel);


    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("station", weatherStation);
        return "home";
    }

    @RequestMapping("/incwind")
    public String incwind(){ //TODO: extend Sensor
        int tmp = windSensor.getValue()+50;
        if(tmp>100)
            tmp-=50;
        windSensor.setValue(tmp);
        return "redirect:/";
    }

    @RequestMapping("/decwind")
    public String decwind(){ //TODO: extend Sensor
        int tmp = windSensor.getValue()-50;
        if(tmp<0)
            tmp=0;
        windSensor.setValue(tmp);
        return "redirect:/";
    }

    @RequestMapping("/inctemperature")
    public String inctemperature(){ //TODO: extend Sensor
        int tmp = temperatureSensor.getValue()+10;
        if(tmp>40)
            tmp=40;
        temperatureSensor.setValue(tmp);
        return "redirect:/";
    }

    @RequestMapping("/dectemperature")
    public String dectemperature(){ //TODO: extend Sensor
        int tmp = temperatureSensor.getValue()-10;
        if(tmp<-20)
            tmp=-20;
        temperatureSensor.setValue(tmp);
        return "redirect:/";
    }


    @RequestMapping("/incbattery")
    public String incbattery(){
        batteryLevel.setLevel(batteryLevel.getLevel()+10);
        return "redirect:/";
    }

    @RequestMapping("/decbattery")
    public String decbattery(){
        batteryLevel.setLevel(batteryLevel.getLevel()-10);
        return "redirect:/";
    }


    /*@RequestMapping("/list")
    public String list(Model model){
        List<Person> data = new LinkedList<>();
        for (Person p: repository.findAll()){
            data.add(p);
        }
        model.addAttribute("people", data);
        return "list";
    }

    @RequestMapping("/create")
    public String create(
            @RequestParam(name="firstname", required=true) String firstname,
            @RequestParam(name="lastname", required=true) String lastname) {
        repository.save(new Person(firstname,lastname));
        return "redirect:/list";
    }



    @RequestMapping("/show")
    public String read(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> result = repository.findById(id);
        //TODO: check if the result is found,
        //TODO: put data in the model field to be displayed in the page
        //TODO: in case no data is found, display the "notfound" page

        if(result.isPresent()){
            model.addAttribute("person", result.get());
            return "show";
        }
        return "notfound";
    }



    @RequestMapping("/edit")
    public String edit(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> result = repository.findById(id);
        //TODO: check if the result is found
        //TODO: put data in the model field to be displayed in the next page to edit them
        //TODO: in case no data is found, display the "notfound" page
        if(result.isPresent()){
            model.addAttribute("person", result.get());
            return "edit";
        }
        return "notfound";
    }

    @RequestMapping("/update")
    public String update(
            @RequestParam(name="id", required=true) Long id,
            @RequestParam(name="firstname", required=true) String firstname,
            @RequestParam(name="lastname", required=true) String lastname,
            Model model) {
        Optional<Person> result = repository.findById(id);
        //TODO: check if the result is found
        //TODO: delete the old person and add a new person
        //TODO: in case no data is found, display the "notfound" page
        if(result.isPresent()){
            repository.deleteById(id);
            repository.save(new Person(firstname,lastname));
            return "redirect:/list";
        }
        return "notfound";
    }


    @RequestMapping("/delete")
    public String delete(
            @RequestParam(name="id", required=true) Long id) {
        Optional<Person> result = repository.findById(id);
        //TODO: check if the result is found
        //TODO: delete the old person and add a new person
        //TODO: in case no data is found, display the "notfound" page
        if(result.isPresent()){
            repository.deleteById(id);
            return "redirect:/list";
        }
        return "notfound";
    }*/

}