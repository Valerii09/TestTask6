//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.ExperimentalComposeUiApi
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.platform.LocalFocusManager
//import androidx.compose.ui.platform.LocalSoftwareKeyboardController
//import androidx.compose.ui.text.input.ImeAction
//import com.example.testtask6.ui.theme.TestTask6Theme
//
//@OptIn(ExperimentalComposeUiApi::class)
//@Composable
//fun MainScreen(
//    onSearch: (String) -> Unit
//) {
//    var searchText by remember { mutableStateOf("") }
//
//    val focusManager = LocalFocusManager.current
//    val keyboardController = LocalSoftwareKeyboardController.current
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Spacer(modifier = Modifier.height(16.dp))
//
//        BasicTextField(
//            value = searchText,
//            onValueChange = {
//                searchText = it
//                onSearch(it)
//            },
//            keyboardOptions = KeyboardOptions.Default.copy(
//                imeAction = ImeAction.Done
//            ),
//            keyboardActions = KeyboardActions(
//                onDone = {
//                    focusManager.clearFocus()
//                    keyboardController?.hide()
//                }
//            ),
//            textStyle = LocalTextStyle.current.copy(color = Color.Black),
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.White)
//                .padding(16.dp)
//                .height(56.dp)
//        )
//    }
//}
