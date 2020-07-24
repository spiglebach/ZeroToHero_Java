package hu.zerotohero.verseny.squares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/squares")
public class SquaresController {
    @Autowired
    private SquaresService squaresService;

    @GetMapping("/getNumberOfSquares")
    public Integer getNumberOfSquares(@RequestBody List<Point> listOfPoints) {
        return squaresService.findPoints(listOfPoints);
    }
}
