package com.alienlab.tracingSystem.service;

import com.alienlab.tracingSystem.Repository.DescItemRepository;
import com.alienlab.tracingSystem.Repository.FarmItemRepository;
import com.alienlab.tracingSystem.entity.DescItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by master on 2017/3/26.
 */
@Service
public class DescItemService {
    @Autowired
    private DescItemRepository descItemRepository;
    @Autowired
    private FarmItemRepository farmItemRepository;
    //通过详情的id找到该条对应的详情
    public DescItem getDescItemById(Long id){
        DescItem item=descItemRepository.findOne(id);
        return item;
    }
    //添加详情
    public DescItem addDesc(DescItem descItem) {
        DescItem item=descItemRepository.save(descItem);
        return item;
    }
    //删除某一详情
    public boolean deleteDesc(long itemid){
        try {
            DescItem item = descItemRepository.findOne(itemid);
          //  batchItemRepository.deleteItemByDetailContentId(item);
            farmItemRepository.deleteByDescItemByDetailcontentId(item);
        //    productItemRepository.deleteByDescItemByDetailcontentId(item);
            descItemRepository.delete(itemid);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
