package lk.ijse.dep10.gallery.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.servlet.ServletContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ServletContext servletContext;

    @GetMapping
    public List<String> getAllImages(UriComponentsBuilder uriBuilder){
        ArrayList<String> imageFileList = new ArrayList<>();
        String imgDirPath = servletContext.getRealPath("/images");
        File imgDir = new File(imgDirPath);
        String[] imageFileNames = imgDir.list();
        for (String imageFileName : imageFileNames) {
            UriComponentsBuilder cloneBuilder = uriBuilder.cloneBuilder();
            String url = cloneBuilder
                    .pathSegment("images",imageFileName).toUriString();
            imageFileList.add(url);
        }
        return imageFileList;
    }
}
