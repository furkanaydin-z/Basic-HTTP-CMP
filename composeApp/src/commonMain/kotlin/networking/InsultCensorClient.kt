package networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import util.NetworkError
import util.Result

class InsultCensorClient(
    private val httpClient: HttpClient
) {
    suspend fun getUserInfo(userId:Int):Result<UserInfo,NetworkError>{
        return try {
            val response:HttpResponse = httpClient.get("https://jsonplaceholder.typicode.com/users/$userId")
            if (response.status.isSuccess()){
                val userInfo:UserInfo = response.body()
                Result.Success(userInfo)
            }else{
                Result.Error(NetworkError.UNKNOWN)
            }
        }catch (e: Exception){
            Result.Error(NetworkError.UNKNOWN)
        }
    }
}