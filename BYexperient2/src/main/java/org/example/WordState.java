package org.example;

public enum WordState {
    START,WORD,INT,FLOAT,OPERATE,OPERATE2,SYMBOL,WORDEND,INTEND,INVALID,
    FLOATEND,OPERATEEND,SYMBOLEND;

    static WordState transfer(WordState state,char c){
        switch (state){
            case WORD:
                if(Character.isLetter(c)||Character.isDigit(c)||c=='_') //回到本状态
                    return WORD;
                else
                    return WORDEND;
            case INT:
                if(Character.isDigit(c))
                    return INT;
                else if(Character.isLetter(c))
                    return INVALID;
                else if(c=='.')
                    return FLOAT;
                else return INTEND;
            case FLOAT:
                if (Character.isLetter(c))
                    return INVALID;
                if (Character.isDigit(c))
                    return FLOAT;
                else return FLOATEND;
            case OPERATE:
                if (c=='=')
                    return OPERATE2;
                else return SYMBOLEND;
            case START:
                if (Character.isLetter(c))
                    return WORD;
                else if (Character.isDigit(c))
                    return INT;
                else if (c=='='||c=='>'||c=='<'||c=='!')
                    return OPERATE;
                else if (c==' ')
                    return START;
                else return SYMBOL;
            case OPERATE2:
                return OPERATEEND;
            case SYMBOL:
                return SYMBOLEND;

            default: return state;
        }
    }


}
