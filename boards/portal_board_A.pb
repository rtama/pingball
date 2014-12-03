board name=Mars

# define some balls
ball name=Ball x=1.8 y=4.5 xVelocity=-3.4 yVelocity=-2.3 
ball name=Ball2 x=7.8 y=1.5 xVelocity=3.4 yVelocity=-2.3 
ball name=Ball3 x=12.8 y=17.5 xVelocity=3.4 yVelocity=2.3 

# define some bumpers
squareBumper name=Square x=3 y=12
circleBumper name=Circle x=14 y=3
triangleBumper name=Tri x=1 y=1 orientation=270

triangleBumper name=del x=19 y=0 orientation=90

# define an absorber to catch the ball
absorber name=Abs x=0 y=19 width=20 height=1 

# make the absorber self-triggering
fire trigger=Abs action=Abs 

# make two portals
portal name=Alpha x=5 y=7 otherPortal=Beta
portal name=Beta x=15 y=7 otherBoard=Mercury otherPortal=Gamma