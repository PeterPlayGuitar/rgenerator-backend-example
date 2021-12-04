package ru.peter.api.account.routes;

import ru.peter.api.common.routes.CommonRoutes;

public class AccountRoutes {

    public static final String ROOT_NOT_SECURE = CommonRoutes.NOT_SECURE + CommonRoutes.V1 + "/accounts";

    public static final String BY_ID = CommonRoutes.V1 + "/accounts/{id}";
    public static final String MY_ACCOUNT = CommonRoutes.V1 + "/accounts/my-account";
}
