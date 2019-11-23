import java.lang.IllegalArgumentException
import kotlin.collections.ArrayList

data class User(var id: String, var userName: String, var name: String, var surName: String)
data class Tag(var id: String, var name: String)

fun main() {
    var appActive = true
    var searchedUsersList = mutableListOf<String>()
    var searchedUsersListNoDup = searchedUsersList.distinct()
    addAlphabet()
    while (appActive) {
        println("recently checked  users $searchedUsersListNoDup")

        val userSuggestionController: SuggestionController<User> = UserSuggestionController()
        var searchedUsers = userSuggestionController.search()
        var searchedUsersObj = getUserByUserName(searchedUsers)
        println("$searchedUsersObj")
        searchedUsersList.add(searchedUsers)

        val tagSuggestionController: SuggestionController<Tag> = TagSuggestionController()
        val searchedTags = tagSuggestionController.search()
        println("To close the app Type Y")
        var close = readLine()?.toLowerCase()
        appActive = close != "y"
    }
}

val userNamesList: ArrayList<String> = ArrayList()
fun addAlphabet() {
    var c = 'a'
    while (c <= 'z') {
        var x = c.toString()
        userNamesList.add(x)
        ++c
    }
}

fun getUserInput(): String {
    var isValidInput = false
    var userChoise = ""
    println("enter on of these ")
    for (userName in userNamesList) {
        print("$userName ")
    }
    while (!isValidInput) {
        var userInput = readLine()
        if (userInput != null && userInput in userNamesList) {
            isValidInput = true
            userChoise = userInput
        }
        if (!isValidInput) println("Enter Valid choice")
    }
    return userChoise
}

fun getUserInputTag(): String {
    var isValidInput = false
    var userChoise = ""
    println("enter on of these ")


    while (!isValidInput) {
        var userInput = readLine()
        if (userInput != null && userInput in userNamesList) {
            isValidInput = true
            userChoise = userInput
        }
        if (!isValidInput) println("Enter Valid choice")
    }
    return userChoise
}

fun getUserByUserName(a: String): User =
    userList.find { it.userName == a } ?: throw IllegalArgumentException("no Such User for $a")

class UserSuggestionController() : SuggestionController<User> {
    override fun search(): String {
        return getUserInput()
    }
}

class TagSuggestionController() : SuggestionController<Tag> {
    override fun search(): String {
       return getUserInputTag()
    }
}

interface SuggestionController<T> {
    fun search(): String
}
