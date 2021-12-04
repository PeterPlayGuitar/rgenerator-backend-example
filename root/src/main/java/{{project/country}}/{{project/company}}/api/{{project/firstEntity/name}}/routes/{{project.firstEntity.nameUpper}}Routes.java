package ru.peter.api.{{project.firstEntity.name}}.routes;

import ru.peter.api.common.routes.CommonRoutes;

public class {{project.firstEntity.nameUpper}}Routes {

    public static final String ROOT = CommonRoutes.V1 + "/{{project.firstEntity.endpointName}}";
    public static final String BY_ID = ROOT + "/{id}";
}
