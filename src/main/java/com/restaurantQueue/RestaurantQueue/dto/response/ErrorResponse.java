package com.restaurantQueue.RestaurantQueue.dto.response;


public class ErrorResponse {

    private int statusCode;
    private String title;
    private String message;


    public ErrorResponse(int statusCode, String title, String message) {
        this.statusCode = statusCode;
        this.title = title;
        this.message = message;
    }

    public static Builder builder(){
        return new Builder();
    }


    public static  class  Builder{

        private int statusCode;
        private String title;
        private String message;

        public  ErrorResponse build(){
            return new ErrorResponse(statusCode,title,message);
        }

        public Builder setStatusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
    }
}
