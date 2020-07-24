package hu.zerotohero.verseny.httpstatus;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("http-status")
public class HTTPStatusController {

    @GetMapping("getStatusDescription")
    public ResponseEntity<String> getStatusDescription(@RequestParam(value = "statusCode", defaultValue = "500") Integer statusCode) {
        HttpStatus requestedStatus = HttpStatus.resolve(statusCode);

        String response = "";
        HttpStatus responseStatus;

        if (requestedStatus != null) {
            response = requestedStatus.getReasonPhrase();
            responseStatus = HttpStatus.OK;
        } else {
            response = "No such status code!";
            responseStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, responseStatus);
    }
}
