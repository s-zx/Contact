package com.example.contact.controller;
import com.example.contact.Body;
import com.example.contact.Consultation;
import com.example.contact.InfoVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.rocksdb.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/consultations")
public class ConsultationController {
    private final RocksDB rocksDB;
    @Autowired
    public ConsultationController() throws RocksDBException {
        // 打开或创建RocksDB数据库
        Options options = new Options().setCreateIfMissing(true);
        rocksDB = RocksDB.open(options, "/opt/homebrew/opt/rocksdb");
    }
    @PostMapping
    public String saveConsultation(@RequestBody List<Consultation> consultations) throws RocksDBException {
        Body consultbody = new Body();
        for (Consultation consultation : consultations) {
            if (consultation.getName().equals("name")){
                consultbody.setName(consultation.getValue());
            }else if (consultation.getName().equals("tel")){
                consultbody.setTel(consultation.getValue());
            }else if (consultation.getName().equals("nianji")){
                consultbody.setNianji(consultation.getValue());
            }else if (consultation.getName().equals("zhiwei")){
                consultbody.setZhiwei(consultation.getValue());
            }else if (consultation.getName().equals("info")){
                consultbody.setInfo(consultation.getValue());
            }
            System.out.println(consultation);
        }
        // 将咨询信息存储到RocksDB数据库中
        if (consultbody.getId() == null) {
            // 处理id为null的情况，可以生成一个唯一的id并设置到Consultation对象中
            Long generatedId = Body.generateUniqueId(); // 自定义生成唯一id的逻辑
            consultbody.setId(generatedId);
        }
        byte[] key = consultbody.getId().toString().getBytes();
        byte[] value = consultbody.toString().getBytes();
        rocksDB.put(key, value);
        return "咨询已保存";
    }

    @GetMapping("/get")
    public List<InfoVO> getInfo() throws RocksDBException {
        List<InfoVO> consultationResponses = new ArrayList<>();

        RocksIterator iterator = rocksDB.newIterator();
        iterator.seekToFirst();

        while (iterator.isValid()) {
            byte[] keyBytes = iterator.key();
            byte[] valueBytes = iterator.value();
            String valueString = new String(valueBytes);
            System.out.println("Value: " + valueString);
            // 移除开头的"Body"和开头的"{"
            String trimmed = valueString.substring(valueString.indexOf("{") + 1);
            // 移除结尾的"}"
            trimmed = trimmed.substring(0, trimmed.lastIndexOf("}"));
            String[] parts = trimmed.split(",");
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode jsonNode = objectMapper.createObjectNode();
            InfoVO info = new InfoVO();
            for (String part : parts) {
                String[] keyValue = part.split("=");
                String key = keyValue[0].trim(); // 去除 "Body." 前缀
                String value = keyValue[1].trim().replaceAll("'", ""); // 去除单引号
                switch (key) {
                    case "id":
                        info.setId(Long.parseLong(value));
                        break;
                    case "name":
                        info.setName(value);
                        break;
                    case "tel":
                        info.setTel(value);
                        break;
                    case "nianji":
                        info.setNianji(value);
                        break;
                    case "zhiwei":
                        info.setZhiwei(value);
                        break;
                    case "info":
                        info.setInfo(value);
                        break;
                    default:
                        break;
                }
            }
            System.out.println(info);
            consultationResponses.add(info);

            iterator.next();
        }

        return consultationResponses;
    }

}
