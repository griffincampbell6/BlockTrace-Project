package edu.example.blockTraceData;

public class ResponseStatus
{
    public boolean isValid;
    public String errorMessage;
    public ResponseStatus(boolean newStatus, String newMessage)
    {
        isValid = newStatus;
        errorMessage = newMessage;
    }
}
