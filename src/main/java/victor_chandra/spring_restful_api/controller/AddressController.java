package victor_chandra.spring_restful_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import victor_chandra.spring_restful_api.entity.User;
import victor_chandra.spring_restful_api.model.AddressResponse;
import victor_chandra.spring_restful_api.model.CreateAddressRequest;
import victor_chandra.spring_restful_api.model.WebResponse;
import victor_chandra.spring_restful_api.service.AddressService;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(
            path = "/api/contacts/{contactId}/addresses"
    )
    public WebResponse<AddressResponse> create(User user,
                                               @RequestBody CreateAddressRequest request,
                                               @PathVariable("contactId") String contactId) {
        request.setContactId(contactId);
        AddressResponse addressResponse = addressService.create(user, request);
        return WebResponse.<AddressResponse>builder().data(addressResponse).build();
    }
}
