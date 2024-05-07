package com.tanhua.dubbo.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "visitors")
public class Visitors implements Serializable {
    private static final long serialVersionUID = 1L;

    private ObjectId id;
    private Long userId;
    private Long visitorUserId;
    private String from;
    private Long date;

    private Double score;
}
