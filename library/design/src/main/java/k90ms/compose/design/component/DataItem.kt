package k90ms.compose.design.component

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import k90ms.compose.design.preview.ThemePreviews
import k90ms.compose.design.theme.ComposeTheme

@Composable
fun DataItem(
    title: String,
    isFavorite: Boolean,
    onItemClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .clickable { onItemClick() },
        horizontalAlignment = Alignment.End
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ThemePreviews
@Composable
private fun ItemPrev() {
    ComposeTheme {
        Scaffold {
            DataItem(
                title = "Test111",
                isFavorite = false,
                onItemClick = {

                },
                onFavoriteClick = {

                }
            )
        }
    }
}