const modalConst = 
{
    open()
    {
        document
            .querySelector('.modal')
            .classList
            .add('active')

    },
    close()
    {
        document
            .querySelector('.modal')
            .classList
            .remove('active')
    }
}