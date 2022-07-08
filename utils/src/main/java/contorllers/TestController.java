package contorllers;


import fileupload.FileUploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class TestController {

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    @PostMapping(value = "/uploac")
    public void upload(@RequestBody MultipartFile multipartFile) throws IOException {
        new FileUploadUtils(multipartFile).saveFile();
    }

}
