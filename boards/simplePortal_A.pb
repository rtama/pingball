board name=SimplePortalA
# define some balls
ball name=Ball x=5 y=0 xVelocity=0 yVelocity=0

portal name=A x=19 y=10 otherBoard=SimplePortalB otherPortal=B
absorber name=abs x=0 y=18 width=20 height=2

fire trigger=abs action=abs