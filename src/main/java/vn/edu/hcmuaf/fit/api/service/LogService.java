package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.LogDTO;
import vn.edu.hcmuaf.fit.api.model.Log;

import java.util.List;

public interface LogService {
    Log saveLog(int userId, LogDTO logDTO);
    List<Log> getLogs();
    Log getLogByID(Integer id);
    Log updateLogByID(Integer id, LogDTO logDTO);
    void deleteLogByID(Integer id);

}
