package com.codermonkeys.chatbot.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codermonkeys.chatbot.R
import com.codermonkeys.chatbot.data.Message
import com.codermonkeys.chatbot.utils.Constants.RECEIVE_ID
import com.codermonkeys.chatbot.utils.Constants.SEND_ID
import kotlinx.android.synthetic.main.message_item.view.*

class MessagingAdapter: RecyclerView.Adapter<MessagingAdapter.MessagingViewHolder>() {

    var messageList = mutableListOf<Message>()

    inner class MessagingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                messageList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagingViewHolder {
        return MessagingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false))
    }

    override fun onBindViewHolder(holder: MessagingViewHolder, position: Int) {

        val currentMessage = messageList[position]

        when(currentMessage.id) {
            SEND_ID -> {
                holder.itemView.tv_message.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tv_bot_message.visibility = View.GONE
            }

            RECEIVE_ID -> {
                holder.itemView.tv_bot_message.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tv_message.visibility = View.GONE
            }
        }
    }

    override fun getItemCount() = messageList.size

    fun insertMessaage(message: Message) {
        this.messageList.add(message)
        notifyItemInserted(messageList.size)
    }
}