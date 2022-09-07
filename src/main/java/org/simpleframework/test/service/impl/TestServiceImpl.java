package org.simpleframework.test.service.impl;

import org.simpleframework.core.annotation.Service;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.test.mapper.TestMapper;
import org.simpleframework.test.service.TestService;

/**
 * @author fa
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

}
