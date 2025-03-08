package in.springsecurity.config;

public final class ApiEndpoints {

        private ApiEndpoints() {
                // Private constructor to prevent instantiation
        }

        // 🔹 AUTH API ENDPOINTS
        public static final String AUTH_URL = "/api/v1/auth";

        public static final String USER_SIGNUP = "/user-signup";
        public static final String CONTENT_MANAGER_SIGNUP = "/content-manager-signup";
        public static final String BLOG_MANAGER_SIGNUP =  "/blog-manager-signup";
        public static final String USER_SIGNIN =  "/userSignIn";
        public static final String OTP_LOGIN =  "/login-otp";
        public static final String OTP_VALIDATE ="/validate-otp";

        // 🔹 CAR API ENDPOINTS
        public static final String CAR_URL = "/api/v1/car";

        public static final String ADD_BRAND_URL =  "/Add-brand";
        public static final String ADD_FUELTYPE_URL =   "/Add-fuelType";
        public static final String ADD_MODEL_URL =  "/Add-model";
        public static final String ADD_TRANSMISSION_URL = "/Add-transmissionType";
        public static final String ADD_YEAR_URL =  "/Add-year";
        public static final String ADD_CARSTATUS_URL = "/Add-status";
        public static final String ADD_CAR_URL =  "/Add-car";

        // 🔹 UPDATE ENDPOINTS
        public static final String UPDATE_CAR_STATUS_URL =  "/{carId}/update-status";

        // 🔹 GET ENDPOINTS
        public static final String GET_ALL_CARS_URL =  "/getAll";  //
        public static final String GET_PARAM_URL =  "/get-byParam";

        // 🔹 CAR IMAGE CONTROLLER ENDPOINTS
        public static final String IMAGE_BASE_URL = "/api/v1/carImages";

        public static final String UPLOAD_FILE_URL = "/upload/file/{bucketName}/car/{carId}";
        public static final String GET_ALL_URL = "/get-allCarImages";


        // 🔹  EVALUATION CONTROLLER ENDPOINTS

        //AREA CONTROLLER

        public  static  final String BASE_AREA_URL = "/api/v1/carArea";

        public  static  final String ADD_AREA_URL = "/add-area";




        // Car evaluation controller
        public static final String BASE_EVAL_URL = "/api/v1/agent";

        public static final String AGENT_ADD_URL= "/add-agent";



        // base customervisitController

        public static  final String BASE_CUSTOMER_VISIT = "/api/v1/customerVisit";
        public static  final String AREA_CUSTOMER_VISIT_URL = "add-customerVisit";


        // 🔹 CRM CONTROLLER ENDPOINTS

        public  static  final String BASE_CRM_URL = "/api/v1/crm";
        public  static  final String SEARCH_AGENT_URL = "get-agent-list";
        public  static  final String ALLOCATE_AGENT_URL = "update-agent";


        // 🔹 TWILIO CRM CONTROLLER ENDPOINTS

        //sms
        public  static  final String SMS_URL = "/api/v1/sms";
        public  static  final String SMS_USER_URL = "/send";


        //whatsapp
        public  static  final String WHATSAPP_URL = "/api/v1/wapp";
        public  static  final String WHATSAPP_SMS_URL = "/send";



        // actual car images controller endpoints

        public  static  final String CAR_EVAL_URL = "/api/v1/evaluatedImages";

        public  static  final String ADD_CAR_EVAL_IMAGES = "/add/file/{bucketName}/car/{carId}";

}
