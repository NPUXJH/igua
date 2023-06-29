package com.wish.hearthotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wish.hearthotel.entities.Notice;
import com.wish.hearthotel.mapper.NoticeMapper;
import com.wish.hearthotel.service.NoticeService;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
}
