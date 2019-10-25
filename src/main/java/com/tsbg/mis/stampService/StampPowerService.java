package com.tsbg.mis.stampService;

import com.tsbg.mis.util.ResultUtils;

public interface StampPowerService {

    ResultUtils addPowerMembers(String userCode);

    ResultUtils selectRoleByUserCode(String userCode);

    ResultUtils deleteStampMemByUserCode(String userCode);

    ResultUtils selectPowerList();
}
