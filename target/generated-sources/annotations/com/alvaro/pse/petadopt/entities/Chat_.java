package com.alvaro.pse.petadopt.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-05-03T11:44:30")
@StaticMetamodel(Chat.class)
public class Chat_ { 

    public static volatile SingularAttribute<Chat, Long> idReceptor;
    public static volatile SingularAttribute<Chat, Long> idConversacion;
    public static volatile SingularAttribute<Chat, Long> idEmisor;
    public static volatile SingularAttribute<Chat, String> mensaje;
    public static volatile SingularAttribute<Chat, String> timestamp;

}