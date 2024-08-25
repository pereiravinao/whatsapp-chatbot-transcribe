# WhatsApp Chatbot with Transcription

## Introduction
This project implements a WhatsApp chatbot that automatically transcribes voice messages into text. It leverages the Evolution API and Groq's Speech-to-Text API (powered by Whisper) to deliver accurate and fast transcriptions.

## How It Works
1. **Voice Message Sent**: A user sends a voice message to a WhatsApp number registered with the Evolution API.
2. **Message Forwarding**: The Evolution API encodes the voice message in Base64 and sends it to the webhook endpoint of this service.
3. **Processing**: The service receives the message, decodes it, and sends the audio to Groq's Speech-to-Text API using Whisper for transcription.
4. **Transcription**: Groq's API processes the audio and returns the transcribed text.
5. **Response**: The service sends the transcription back to the user via WhatsApp.

## Features
- **Real-time Transcription**: Quickly transcribes voice messages to text and sends the response back to the user.
- **Accurate Transcriptions**: Utilizes Groq's Whisper technology for high-quality speech recognition.
- **Seamless Integration**: Fully integrated with the Evolution API for smooth communication between WhatsApp and the transcription service.

## How to Use
1. **Send a Voice Message**: Send a voice message to the WhatsApp number registered with the Evolution API.
2. **Receive Transcription**: Wait a moment while the service processes the message and sends the transcription back to you.
3. **Enjoy**: Your voice message is now transcribed into text and delivered back to you via WhatsApp.

## Requirements
- **WhatsApp Account**: Ensure that the WhatsApp number is registered and set up with the Evolution API.
- **Server Setup**: The service should be hosted on a server with network access to both the Evolution API and Groq's API.
- **API Keys**: Obtain API keys for both the Evolution API and Groq's Speech-to-Text API (Whisper).

## Installation
To set up the service locally or on a server:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/pereiravinao/whatsapp-java.git
   cd whatsapp-java
