package networking

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(val id: Int,
                    val name: String,
                    val username: String,
                    val email: String)
