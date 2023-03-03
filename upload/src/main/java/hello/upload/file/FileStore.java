package hello.upload.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import hello.upload.domain.UploadFile;

@Component
public class FileStore {
    
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename){
        return fileDir + filename;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IllegalStateException, IOException{
        List<UploadFile> storeFileResult = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles){
            if(!multipartFile.isEmpty()){
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IllegalStateException, IOException{
        if(multipartFile.isEmpty()){
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = createStoreFilename(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFilename)));

        return new UploadFile(originalFilename, storeFilename);
    }

    private String createStoreFilename(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        String storeFilename = uuid+"."+ext;
        return storeFilename;
    }

    private String extractExt(String orginalFilename){
        int pos = orginalFilename.lastIndexOf(".");
        return orginalFilename.substring(pos + 1);
    }
}
