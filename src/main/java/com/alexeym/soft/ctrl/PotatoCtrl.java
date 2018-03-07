package com.alexeym.soft.ctrl;

import com.alexeym.soft.model.PotatoBag;
import com.alexeym.soft.storage.PotatoStorage;
import com.alexeym.soft.validator.PotatoValidator;
import com.alexeym.soft.validator.ValidationResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.alexeym.soft.ctrl.PotatoCtrl.POTATO_BAG_URL;

/**
 * Created by Alexey Matveev on 3/7/2018.
 */
@Api(value = POTATO_BAG_URL, description = "Potato bag controller")
@RestController
public class PotatoCtrl {

    public static final String POTATO_BAG_URL = "/potato-bag";

    @Autowired
    private PotatoStorage storage;

    @Autowired
    private PotatoValidator validator;


    @ApiOperation(value = "List all potato bags",
            notes = "Can restrict the size of returned bag list with size param (default size is 3)",
            response = PotatoBag.class)
    @RequestMapping(value = POTATO_BAG_URL, method = RequestMethod.GET)
    public List<PotatoBag> list(@RequestParam(value = "size", required = false, defaultValue = "3") int size) {
        List<PotatoBag> list = storage.list();
        if (list == null || size == 0) {
            return new ArrayList<>();
        } else {
            return list.subList(0, size < list.size() ? size : list.size());
        }
    }

    @ApiOperation(value = "Creates new potato bag",
            notes = "The following validation rules must be considered:\n" +
                    "- Number of potatoes in a bag: 1 - 100\n" +
                    "- Supplier one of: De Coster, Owel, Patatas Ruben, Yunnan Spices\n" +
                    "- Pack date: not in future\n" +
                    "- Price between: 1 - 50\n\n" +
                    "If smth is not correct - 400 will be returned",
            response = PotatoBag.class)
    @RequestMapping(value = POTATO_BAG_URL, method = RequestMethod.POST)
    public PotatoBag add(@RequestBody PotatoBag bag, HttpServletResponse response) throws IOException {
        ValidationResult result = validator.validatePotatoBag(bag);
        if (result.isValid()) {
            return storage.add(bag);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(result.getError());
            return null;
        }
    }

}
