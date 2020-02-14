package com.info.hunar.api_url;

import com.info.hunar.model_pojo.Welcome_Video_Model;
import com.info.hunar.model_pojo.category_model.CategoryModel;
import com.info.hunar.model_pojo.quiz_test_model.QuizTestModel;
import com.info.hunar.model_pojo.registration_model.RegistrationModel;
import com.info.hunar.model_pojo.subcategory_course_model.Sub_course_details_model;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static com.info.hunar.api_url.Base_Url.get_category;
import static com.info.hunar.api_url.Base_Url.get_quiz_question;
import static com.info.hunar.api_url.Base_Url.get_subcategory;
import static com.info.hunar.api_url.Base_Url.getwelcomevideo;
import static com.info.hunar.api_url.Base_Url.registration;
import static com.info.hunar.api_url.Base_Url.subcategory_detail;
import static com.info.hunar.api_url.Base_Url.user_login;

/**
 * Created by Raghvendra Sahu on 15-Jan-20.
 */
public interface Api_Call {

    @GET(get_category)
    Observable<CategoryModel> GetCategory();

    @POST(get_subcategory)
    @FormUrlEncoded
    Observable<CategoryModel> GetSubCategory(
            @Field("id") String category_id);

    @POST(subcategory_detail)
    @FormUrlEncoded
    Observable<Sub_course_details_model> GetSubCategoryDetails(
            @Field("subcategory_id") String subCategory_id);

    @GET(getwelcomevideo)
    Observable<Welcome_Video_Model> Get_Welcome_Video();

    @POST(get_quiz_question)
    @FormUrlEncoded
    Observable<QuizTestModel> GetQuizTest(
            @Field("subcategory_id") String subCategory_id);

    @POST(registration)
    @FormUrlEncoded
    Observable<RegistrationModel> RegistrationUser(
            @Field("name") String name,
            @Field("user_email") String email,
            @Field("password")String password,
            @Field("mobile_no") String mobile,
            @Field("address") String address,
            @Field("gender") String gender);

    @POST(user_login)
    @FormUrlEncoded
    Observable<RegistrationModel> LoginUser(
            @Field("user_email") String email,
            @Field("password")String password);



//    @FormUrlEncoded
//    @POST(Base_Url.get_member_profile)
//    Call<ProfileDeailsModel> GetProfileImage(
//            @Field("id") String user_id);
//
//
//    @GET(Base_Url.get_countries)
//    Call<CountryDeailsModel> GetAllCountry();
}