package com.wish.hearthotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wish.hearthotel.entities.Repairs;
import com.wish.hearthotel.mapper.RepairsMapper;
import com.wish.hearthotel.service.RepairsService;
import org.springframework.stereotype.Service;


@Service
public class RepairsServiceImpl extends ServiceImpl<RepairsMapper, Repairs> implements RepairsService {
}
