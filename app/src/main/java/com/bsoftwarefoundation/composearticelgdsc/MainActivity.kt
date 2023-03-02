package com.bsoftwarefoundation.composearticelgdsc

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.sharp.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bsoftwarefoundation.composearticelgdsc.ui.theme.ComposeArticelGDSCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArticelGDSCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArticleApp(
                        headertitile = stringResource(R.string.headertitle_article),
                        paragraph1 =  stringResource(R.string.paragraph1),
                        paragraph2 = stringResource(R.string.paragraph2),
                        footertext = stringResource(R.string.footertext_article)
                    )
                }
            }
        }
    }
}

@Composable
fun ArticleApp(headertitile : String,paragraph1 : String,paragraph2: String,footertext : String){
    Column{
        HeaderArticle()
        Column(modifier = Modifier.verticalScroll(rememberScrollState())){
            ArticleContent(headertitile = headertitile, paragraph1 = paragraph1, paragraph2 = paragraph2 )
            FooterArticle(text = footertext)
        }
    }
}

@Composable
fun HeaderArticle(){
    val gdscImage = painterResource(id = R.drawable.gdsc_logo)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.Top){

       Icon(
           Icons.Rounded.AccountCircle,
           contentDescription = null,
           modifier = Modifier.size(60.dp,50.dp)
       )
       Image(
           painter = gdscImage,
           contentDescription = null,
           modifier = Modifier
               .fillMaxWidth()
               .wrapContentWidth(Alignment.CenterHorizontally)
               .size(60.dp, 50.dp)

       )
    }
}

@Composable
fun ArticleContent(headertitile : String,paragraph1 : String,paragraph2: String){
    val imageBanner = painterResource(id = R.drawable.bg_compose_background)

    Column{
        Image(painter = imageBanner, contentDescription = null)
        Text(
            text = headertitile,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(15.dp)
        )

        Text(
            text = paragraph1,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )

        Text(
            text = paragraph2,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun FooterArticle(text : String){

    val sentimentSatisfied = painterResource(id = R.drawable.sentiment_satisfied)
    val sentimentNeutral = painterResource(id = R.drawable.sentiment_neutral)
    val sentimentDissatisfied = painterResource(id = R.drawable.sentiment_dissatisfied)
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(top = 20.dp)
    ){

        Text(text = text)

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ){
            // Happy icon button
            IconButton(
                onClick = {
                   Toast.makeText(context,"Thank you for Satisfied :)",Toast.LENGTH_SHORT).show()
                }
            ) {
               Icon(
                   painter = sentimentSatisfied,
                   contentDescription = null,
                   modifier = Modifier.size(30.dp,30.dp)
               )
            }

            // Meh icon button
            IconButton(
                onClick = {
                    Toast.makeText(context,"It's Okey thank's! :)",Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(
                    painter = sentimentNeutral,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp,30.dp)
                )
            }

            // Sad icon button
            IconButton(
                onClick = {
                    Toast.makeText(context,"Oh No We say sorry :(",Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(
                    painter = sentimentDissatisfied,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp,30.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ComposeArticelGDSCTheme {
        ArticleApp(
            headertitile = stringResource(R.string.headertitle_article),
            paragraph1 = stringResource(R.string.paragraph1),
            paragraph2 = stringResource(R.string.paragraph2),
            footertext = stringResource(R.string.footertext_article)
        )
        //HeaderArticle()
        /*ArticleContent(
            headertitile = "Jetpack Compose tutorial",
            paragraph1 = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
            paragraph2 = "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\\\\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\\\\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function"
        )*/
        // FooterArticle(text = "This Article Helpfully ?")
    }
}