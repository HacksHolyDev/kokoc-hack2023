package com.holydev.sportcharity.entities.organizations;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CharityCategory {
    ANIMALS("Животный мир"),

    TREES("Растительный мир"),

    ELDERS("Пожилые люди и дома престарелых"),

    CHILDREN("Дети и дома малютки"),

    NO_PARENTS("Сиротские приюты"),

    HOSPICE("Хосписы и лечебницы");

    private final String title;
}
