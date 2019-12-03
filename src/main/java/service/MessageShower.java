package service;

import libs.Message;

import java.util.ArrayList;

public class MessageShower {
    /**
     *
     * @SenderMessageTemplate
     * <li class="send-msg float-right mb-2">
     *      <p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">
     *         _MESSAGE_
     *      </p>
     * </li>
     * @ReceiverMessageTemplate
     * <li class="receive-msg float-left mb-2">
     *    <div class="sender-img">
     *      <img src= _PROFILE_IMG_URL_ class="float-left">
     *    </div>
     *    <div class="receive-msg-desc float-left ml-2">
     *      <p class="bg-white m-0 pt-1 pb-1 pl-2 pr-2 rounded">
     *         _MESSAGE_
     *      </p>
     *
     *      <span class="receive-msg-time">_RECEIVE_TIME_</span>
     *    </div>
     * </li>
     *
     * */
    public ArrayList<Message> get()
    {
        throw new RuntimeException("Not implemented yet");
    }

    public String convertToSenderMessage(String message)
    {
        return "<li class=\"send-msg float-right mb-2\"><p class=\"pt-1 pb-1 pl-2 pr-2 m-0 rounded\">" +
                message +
                "</p></li>";
    }
    public String convertToReceiverMessage(String message, String profileURL, String time)
    {
        return "<li class=\"receive-msg float-left mb-2\"><div class=\"sender-img\"> <img src=\""
                + profileURL +
                "\" class=\"float-left\"></div><div class=\"receive-msg-desc float-left ml-2\"><p class=\"bg-white m-0 pt-1 pb-1 pl-2 pr-2 rounded\">"
                + message +
                "</p><span class=\"receive-msg-time\">" + time + "</div></li>";
    }

}
