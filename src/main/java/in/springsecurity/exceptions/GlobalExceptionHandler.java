package in.springsecurity.exceptions;


import in.springsecurity.payload.exceptionPayload.ApiResponse;
import org.joda.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {


         @ExceptionHandler(DuplicateResourceException.class)
         public ResponseEntity<ApiResponse> handlerDuplicateResourceFoundException(DuplicateResourceException rs, WebRequest req){

             ApiResponse apiResponse = new ApiResponse();
             apiResponse.setDate( new Date());
             apiResponse.setMessage(rs.getMessage());
             apiResponse.setRequest(req.getDescription(true));

             return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
         }





         public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException rs, WebRequest wrl){

             ApiResponse apiResponse = new ApiResponse();
             apiResponse.setRequest(wrl.getDescription(true));
             apiResponse.setDate(new Date());
             apiResponse.setMessage(rs.getMessage());
             return  new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
         }

    }

