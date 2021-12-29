package com.arhostcode.world;

public enum Direction {

    UP {
        public int getTranslationX() {
            return 0;
        }

        public int getTranslationY() {
            return -1;
        }
    },
    DOWN {
        public int getTranslationX() {
            return 0;
        }

        public int getTranslationY() {
            return 1;
        }
    },
    LEFT{
        public int getTranslationX() {
            return -1;
        }
        public int getTranslationY() {
            return 0;
        }
    },
    RIGHT{
        public int getTranslationX() {
            return 1;
        }
        public int getTranslationY() {
            return 0;
        }
    };

    abstract int getTranslationX();

    abstract int getTranslationY();

}
