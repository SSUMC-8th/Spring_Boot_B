package umc.spring.service.tempService;

import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.ErrorStatus;
import umc.spring.apiPayload.exception.handler.TempHandler;

@Service
public class TempQueryServiceImpl implements TempQueryService{

    @Override
    public void checkFlag(Integer flag){
        if(flag==1){
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}
