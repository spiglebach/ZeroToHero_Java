package hu.zerotohero.verseny.numberofsteps;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("number-of-steps")
public class NumberOfStepsController {
    @GetMapping("getNumberOfSteps")
    public ResponseEntity<Integer> getNumberOfSteps(@RequestParam(value = "numberOfStair") Integer numberOfStair, @RequestParam(value = "stepSizeList") Integer[] stepSizeList) {
        return new ResponseEntity<>(possibleStepsFromCurrentStair(numberOfStair, stepSizeList), HttpStatus.OK);
    }

    public int possibleStepsFromCurrentStair(Integer remainingStairs, Integer[] stepSizes) {
        int possibleStepsFromThisPoint = 0;
        for (Integer step : stepSizes) {
            if (remainingStairs >= step) {
                possibleStepsFromThisPoint += possibleStepsFromCurrentStair(remainingStairs - step, stepSizes);
            }
        }
        return possibleStepsFromThisPoint == 0 ? 1 : possibleStepsFromThisPoint;
    }
}
