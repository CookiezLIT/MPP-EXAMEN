package rest;



import model.Propunere;
import model.Solutie;
import model.Utilizator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import repository.PropunereHbRepo;
import repository.SolutieHbRepo;
import repository.UtilizatorRepo;

import java.util.ArrayList;

@RestController
@RequestMapping("/app/")
public class AppRestController {
    private static final String template = "Hello, %s!";

    @Autowired
    private UtilizatorRepo utilizatorRepo;

    @Autowired
    private PropunereHbRepo propunereRepo;

    @Autowired
    private SolutieHbRepo solutieHbRepo;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return String.format(template, name);
    }

//    @RequestMapping("/scores")
//    public String getGames(@RequestParam(value="alias", defaultValue="") String alias ) {
//
//        Utilizator user = utilizatorRepo.findOneByUsername(alias);
//
//        ArrayList<Propunere> propuneri = (ArrayList<Propunere>) propunereRepo.getByUser(user);
//
//        System.out.println("USER IS:");
//        System.out.println(user.getAlias());
//
//        System.out.println("AVEM UN NUMAR DE PROPUNERI DE:");
//        System.out.println(propuneri.size());
//
//        String final_string = "";
//
//        for (Propunere p: propuneri
//             ) {
//            System.out.println("GENIAL");
//            final_string += p.toString();
//            System.out.println(p);
//        }
//
//        return final_string;
//
//    }

    @RequestMapping("/scores")
    public ArrayList<Propunere> getGames(@RequestParam(value="alias", defaultValue="") String alias ) {

        Utilizator user = utilizatorRepo.findOneByUsername(alias);

        ArrayList<Propunere> propuneri = (ArrayList<Propunere>) propunereRepo.getByUser(user);

        System.out.println("USER IS:");
        System.out.println(user.getAlias());

        System.out.println("AVEM UN NUMAR DE PROPUNERI DE:");
        System.out.println(propuneri.size());



        return propuneri;

    }


    @RequestMapping(path="/solution", method = RequestMethod.POST)
    public Solutie create(@RequestBody Solutie solutie){
        solutieHbRepo.save(solutie);
        return solutie;

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String probaError(Exception e) {
        return e.getMessage();
    }
}
