package com.example.bookapp;

public class ModelClass  {

        int imag;
        String text;

        public ModelClass(int imag, String text) {
            this.imag = imag;
            this.text = text;
        }

        public int getImag() {
            return imag;
        }

        public void setImag(int imag) {
            this.imag = imag;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
}
